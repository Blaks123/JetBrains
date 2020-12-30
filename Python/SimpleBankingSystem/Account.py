import random
import banking

IIT = 400000 * (10 ** 9)


def create_by_luhn_algorithm():
    integer_list_cardNo = []
    sum = 0
    index = 0
    cardNo = str((IIT + random.randint(10 ** 8, 999999999)))

    list_cardNo = list(cardNo)

    for i in list_cardNo:
        integer_list_cardNo.append(int(i))

    for i in integer_list_cardNo:
        if index % 2 == 0:
            i *= 2
        if i > 9:
            i -= 9
        sum += i
        index += 1

    sum *= 9
    check_digit = sum % 10

    cardNo += str(check_digit)
    return cardNo


def check_luhn_algorithm(card_number):
    integer_list_cardNo = []
    index = 0
    sum = 0
    cardno = str(card_number)
    list_cardNo = list(cardno)

    for i in list_cardNo:
        integer_list_cardNo.append(int(i))

    for i in range(0, len(integer_list_cardNo) - 1, 1):
        if index % 2 == 0:
            integer_list_cardNo[i] *= 2
        if integer_list_cardNo[i] > 9:
            integer_list_cardNo[i] -= 9
        sum += integer_list_cardNo[i]
        index += 1

    if (sum + card_number % 10) % 10 == 0:
        return True

    return False


class Account:
    ID = 0

    def __init__(self):
        self.card_number = int(create_by_luhn_algorithm())
        self.pin = random.randint(1000, 9999)
        self.balance = 0
        self.id = Account.ID
        banking.add_to_table(self)
        Account.ID += 1

    def __del__(self):
        Account.ID -= 1

    def get_balance(self):
        return self.balance

    def add_balance(self, amount):
        self.balance += amount

    def get_pin(self):
        return self.pin

    def get_card_number(self):
        return self.card_number

    def get_card_id(self):
        return self.id
