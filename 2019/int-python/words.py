import string


words = [x.strip().lower() for x in open('words.txt').readlines()]
print(str(len(words)) + ' words loaded')


def any_word_double(letter):
    double_letter = letter * 2
    for word in words:
        if double_letter in word:
            return True
    return False


def never_doubled_letters():
    letters = list()
    for letter in string.ascii_lowercase:
        if not any_word_double(letter):
            letters.append(letter)
    return letters


def longest_palindromes():
    longest = ''
    for word in words:
        is_palindrome = True
        for i in range(len(word)):
            if word[i] != word[-1-i]:
                is_palindrome = False
                break
        if is_palindrome and len(word) > len(longest):
            longest = word
    return longest



print('Never doubled letters: ' + str(never_doubled_letters()))
print('Longest palindromes: ' + str(longest_palindromes()))

