from enum import Enum


class Game:
    def __init__(self, board, player, mines, exit):
        self.board = board
        self.player = player
        self.mines = mines
        self.exit = exit
        self.validate()

    def validate(self):
        if self.board.width < 1 or self.board.width > 1000:
            raise Exception("Board width must be between 1 and 1000")

        if self.board.height < 1 or self.board.height > 1000:
            raise Exception("Board height must be between 1 and 1000")

        if self.player.face not in Ordinal:
            raise Exception("Player face is invalid")

        if not self.board.valid_position(self.player.x, self.player.y):
            raise Exception("Player position is out of board area")

        if not self.board.valid_position(self.exit.x, self.exit.y):
            raise Exception("Exit position is out of board area")

        for mine in self.mines:
            if not self.board.valid_position(mine.x, mine.y):
                raise Exception(f"Mine position is out of board area")

    def move_player(self, movements):
        result = GameResult()

        actions = {
            'm': self.player.move,
            'r': self.player.rotate
        }

        for index, movement in enumerate(movements):
            event = 'Success'

            if movement not in actions:
                raise Exception(f"Unknown movement \'{movement}\'")

            actions[movement]()

            if not self.board.valid_position(self.player.x, self.player.y):
                raise Exception(f"Player left the board in movement \'{movement}\' at position {index+1}")

            if any(self.player.same_position(mine) for mine in self.mines):
                event = 'Player hit a mine'
                result.mine_hits += 1

            if self.player.same_position(self.exit):
                event = 'Player is in the exit'
                result.found_exit = True

            result.events.append(event)

        return result


class GameResult:
    def __init__(self):
        self.events = []
        self.mine_hits = 0
        self.found_exit = False


class Board:
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def valid_position(self, x, y):
        return 0 <= x < self.width and 0 <= y < self.height


class Position:
    def __init__(self, x, y):
        self.x = x
        self.y = y


class Ordinal(Enum):
    NORTH = 1
    EAST = 2
    SOUTH = 3
    WEST = 4


class Player:
    def __init__(self, x, y, face):
        self.x = x
        self.y = y
        self.face = face

    def same_position(self, element):
        return element.x == self.x and element.y == self.y

    def move(self):
        if self.face == Ordinal.NORTH:
            self.y -= 1
        elif self.face == Ordinal.SOUTH:
            self.y += 1
        elif self.face == Ordinal.EAST:
            self.x += 1
        elif self.face == Ordinal.WEST:
            self.x -= 1

    def rotate(self):
        if self.face == Ordinal.NORTH:
            self.face = Ordinal.EAST
        elif self.face == Ordinal.EAST:
            self.face = Ordinal.SOUTH
        elif self.face == Ordinal.SOUTH:
            self.face = Ordinal.WEST
        elif self.face == Ordinal.WEST:
            self.face = Ordinal.NORTH

