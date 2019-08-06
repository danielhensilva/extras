#!/usr/bin/env bash

python3 -m venv venv
source venv/bin/activate
which pyhon
python --version
pip install flask
pip freeze
pip freeze > requirements.txt
pip install -r requirements.txt