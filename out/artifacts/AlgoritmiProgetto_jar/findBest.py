import subprocess

files=["ch130.tsp","d198.tsp","eil76.tsp","fl1577.tsp","kroA100.tsp","lin318.tsp","pcb442.tsp","pr439.tsp","rat783.tsp","u1060.tsp"]
import subprocess
while True:
        for filename in files:
                subprocess.call(['java', '-jar', 'AlgoritmiProgetto.jar',filename])
