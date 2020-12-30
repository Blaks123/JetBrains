import sqlite3

import Account


import random

card_by_number = {}


conn = sqlite3.connect('card.s3db')
c = conn.cursor()


def print_input_acc(account):
    print("Your card has been created")
    print("Your card number:")
    print(account.get_card_number())
    print("Your card PIN:")
    print(account.get_pin())
    print()


def print_input():
    print("1. Create an account")
    print("2. Log into account")
    print("0. Exit")
    print(">", end='')


def card_info_input(info):
    print(f"Enter your card {info}:")
    print(">", end="")
    ret = int(input())
    return ret


def print_balance_input():
    balance_dict = {"1.": "Balance", "2.": "Add income", "3.": "Do transfer", "4.": "Close account", "5.": "Log out",
                    "0.": "Exit"}
    for key in balance_dict:
        print(key, balance_dict[key])
    print(">", end="")


def balance(account):
    while True:
        print_balance_input()
        m = int(input())
        # print balance
        if m == 1:
            print()
            print("Balance:", account.get_balance())
            print()

        # add income
        elif m == 2:
            print("\nEnter income:")
            print(">", end="")
            income = int(input())
            account.add_balance(income)

            c.execute(f"""UPDATE card       
                         SET balance = {account.get_balance()}
                         WHERE number = {account.get_card_number()}""")
            conn.commit()

            print("Income was added!\n")

        # transfer money
        elif m == 3:
            print("\nTransfer")
            print("Enter card number:")
            print(">", end="")

            card_to = int(input())

            # check for mistakes
            if card_to == account.get_card_number():
                print("You can't transfer money to the same account!\n")
                continue

            if not Account.check_luhn_algorithm(card_to):
                print("Probably you made a mistake in the card number. Please try again!\n")
                continue

            if card_to not in card_by_number.keys():
                print("Such a card does not exist.\n")
                continue

            # transferring amount
            print("Enter how much money you want to transfer:")
            print(">", end="")

            amount = int(input())
            if amount > account.get_balance():
                print("Not enough money!\n")
            else:
                account.add_balance(-amount)
                card_by_number.get(card_to).add_balance(amount)
                c.execute(f"""UPDATE card       
                         SET balance = {account.get_balance()}
                         WHERE number = {account.get_card_number()}""")
                c.execute(f"""UPDATE card       
                         SET balance = {card_by_number.get(card_to).get_balance()}
                         WHERE number = {card_by_number.get(card_to).get_card_number()}""")
                conn.commit()
                print("Success!\n")

        # delete account
        elif m == 4:
            c.execute(f"""DELETE FROM card WHERE number = {account.get_card_number()}""")
            conn.commit()
            del account

        # log out
        elif m == 5:
            print("You have successfully logged out!\n")
            main()

        # exit
        elif m == 0:
            print("Bye!")
            quit()


# def start():


def create_table():
    c.execute("""CREATE TABLE IF NOT EXISTS card (
                    id INTEGER,
                    number TEXT,
                    pin TEXT,
                    balance INTEGER DEFAULT 0)""")
    conn.commit()


def add_to_table(account):
    c.execute("INSERT INTO card VALUES (?, ?, ?, ?)", (account.get_card_id(),
                                                       account.get_card_number(),
                                                       account.get_pin(),
                                                       account.get_balance()))
    conn.commit()


# Main
def main():
    create_table()

    print_input()

    n = int(input())
    print()

    while True:
        if n == 1:
            account = Account.Account()
            card_by_number[account.get_card_number()] = account
            print_input_acc(account)

        elif n == 2:
            cardNo = card_info_input("card number")
            pin = card_info_input("PIN")

            print()

            if cardNo in card_by_number and card_by_number[cardNo].get_pin() == pin:
                print("You have successfully logged in!\n")
                balance(card_by_number[cardNo])
            else:
                print("Wrong card number or PIN!\n")
                main()

        elif n == 0:
            print("Bye!")
            quit()
            break

        print_input()
        n = int(input())
        print()

    # start()

    c.execute("SELECT * FROM card")
    print(c.fetchall())
    conn.commit()
    conn.close()


if __name__ == '__main__':
    main()
