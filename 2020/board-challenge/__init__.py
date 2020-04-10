import sys
import yaml
from os import path
from models import *


def main():
    if len(sys.argv) != 3:
        raise Exception("Expected to be called with two arguments")

    game_settings_path = sys.argv[1]
    movements_path = sys.argv[2]

    if not path.exists(game_settings_path):
        raise Exception("game-settings file not found")

    if not path.exists(movements_path):
        raise Exception("Movements file not found")

    with open(game_settings_path, "r") as stream:
        game_settings = yaml.safe_load(stream)

    with open(movements_path, 'r') as stream:
        movements = stream.readline().split(',')

    mines = []
    for item in game_settings["mines"]:
        mine = Position(item["x"], item["y"])
        mines.append(mine)

    board = Board(
        game_settings["board"]["n"],
        game_settings["board"]["m"])

    player = Player(
        game_settings["player"]["x"],
        game_settings["player"]["y"],
        Ordinal[game_settings["player"]["face"]])

    exit = Position(
        game_settings["exit"]["x"],
        game_settings["exit"]["y"])

    game = Game(board, player, mines, exit)

    result = game.move_player(movements)

    print('Events:')
    for index, event in enumerate(result.events):
        print(f'Sequence {index+1}: {event}')

    print('\nConclusion:')
    print(f'Player has hit {result.mine_hits} mine(s)')
    print('Player has found the exit') \
        if result.found_exit \
        else print('Player has NOT found the exit')

    sys.exit(0)


if __name__ == "__main__":
    main()