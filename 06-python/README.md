# Python example

A very simple Python example.

## Python setup tips

Typically Python is already installed. Otherwise install for your
platform. Example:

brew install python

The most recommended way to install CLI addons is via pipx. Example:

brew install pipx

Then you probably want virtualenv, which acts mostly like a package
manager. It avoids putting all your packages in to the global python.

pipx install virtualenv

## Conda

However, some people install Conda and use it in place of all of the
above. Conda comes in two flavors. Anaconda is a commercial product,
Miniconda is the open source essence.

https://www.anaconda.com/products/individual

https://docs.conda.io/en/latest/miniconda.html

## Creating requirements.txt

Grab a snapshot of packages installed with pip:

Beware that some setups (like Mac Brew) use python and pip for version 2, python3 and pip3 for version 3.

pip3 install numpy

pip3 freeze > requirements.txt

A simple contents might be:

```
numpy==1.16.6
```
