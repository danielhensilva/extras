from basicmath import div


def test_div():
    assert div(4, 2) == 2


def test_div_2():
    assert div(15, 3) == 5


def test_div_for_float():
    assert div(3, 2) == 1.5
