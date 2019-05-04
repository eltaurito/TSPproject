files=["ch130.tsp","d198.tsp","eil76.tsp","fl1577.tsp","kroA100.tsp","lin318.tsp","pcb442.tsp","pr439.tsp","rat783.tsp","u1060.tsp"]
seeds=["1556838468638","1556838308988","1556838319270","1556838323370","1556838335182","1556838345451","1556838355700","1556838541092","1556838551522","1556838561812"]
iterations=["549","234","1633","2","870","77","35","38","9","4"]
import subprocess
while True:
    i=0
    for filename in files:
        seed=seeds[i]
        iteration=iterations[i]
        subprocess.call(['java', '-jar', 'AlgoritmiProgetto.jar',filename,seed,iteration])
        i=i+1
