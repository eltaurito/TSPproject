import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TspManager {
    static List<Solution> solutions;

    public void writeSolution(String path) throws IOException {
        solutions=new ArrayList<>();
        File file=new File(path);
        boolean found=false;
        if(file.exists()){
            FileReader r=null;
            try {
                r=new FileReader(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            BufferedReader b=new BufferedReader(r);
            String line;
            while((line=b.readLine())!=null){
                String[] strings=line.split("-");
                solutions.add(
                        new Solution(
                                strings[0],
                                Long.parseLong(strings[1]),
                                Integer.parseInt(strings[2])
                        )
                );

            }

            Solution newSolution=new Solution(Main.filename,Main.seed,Main.bestResult);
            for (Solution solution : solutions) {
                if (solution.fileName.equals(newSolution.fileName)) {
                    found=true;
                    if (solution.distance > newSolution.distance) {
                        solution.seed=newSolution.seed;
                        solution.distance=newSolution.distance;
                    }
                }
            }
            if(!found)
                solutions.add(newSolution);
        }
        try {
            PrintWriter out=new PrintWriter(path);
            for (Solution s : solutions) {
                out.write(s.fileName+"-"+s.seed+"-"+s.distance+"\n");
            }


            out.close();
        } catch (Exception e) {
            System.out.println("Error occurred while writing");
        }

    }


}

class Solution {
    String fileName;
    long seed;
    int distance;

    public Solution(String fileName,long seed,int distance) {
        this.fileName=fileName;
        this.seed=seed;
        this.distance=distance;
    }

    public String getFileName() {
        return fileName;
    }

    public long getSeed() {
        return seed;
    }

    public int getDistance() {
        return distance;
    }
}
