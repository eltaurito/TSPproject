import subprocess

files=["ch130.tsp","d198.tsp","eil76.tsp","fl1577.tsp","kroA100.tsp","lin318.tsp","pcb442.tsp","pr439.tsp","rat783.tsp","u1060.tsp"]
distances=[6110,15781,538,22737,21282,42098,51073,107298,9039,231594]
dirOld='./Old_Files/'
dirTsps='./src/main/resources/'
import subprocess
i=0
for filename in files:
	name='OPT_'+filename+'.tour'
        subprocess.call(['diff', name,dirOld+name])
        subprocess.call(['python','tspTourChecker.py',dirTsps+filename,name,str(distances[i])])
	i=i+1
