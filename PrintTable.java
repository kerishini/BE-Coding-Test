import java.io.*;
import java.util.*;

public class PrintTable {

    public static void main(String[] args)  {
        System.out.println("Please enter filename: ");
        Scanner getFileContents= new Scanner(System.in);
        String fileName= getFileContents.next();
        TreeMap<String, Integer> teams = new TreeMap<>();
        String fileToStr= "";

        try
        {
            File file=new File(fileName);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer();
            String line;
            while((line=br.readLine())!=null)
            {
                sb.append(line);
                sb.append("\n");
            }
            fr.close();
            fileToStr= sb.toString();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Scanner sc = new Scanner(fileToStr);

        while (sc.hasNextLine()){
            String line= sc.nextLine();
            String[] arrOfLine= line.split(",");

            Match currentGame= new Match();
            for (int i=0; i<2; i++){
                String a = arrOfLine[i].trim();
                String[] arrOfTeam = a.split("(?<=\\D)(?=\\d)");
                String teamName = arrOfTeam[0].trim();
                Team team= new Team();
                team.setName(teamName);
                int teamScore = Integer.parseInt(arrOfTeam[1].trim());
                if (i==0){
                    currentGame.setTeamOne(team);
                    currentGame.setScoreOne(teamScore);
                }
                else{
                    currentGame.setTeamTwo(team);
                    currentGame.setScoreTwo(teamScore);
                }
            }


            if (currentGame.getScoreOne() > currentGame.getScoreTwo()){
                Team teamOne= currentGame.getTeamOne();
                Team teamTwo= currentGame.getTeamTwo();
                int teamOneScore= 3;
                int teamTwoScore= 0;
                teamOne.setScore(teamOneScore);
                teamTwo.setScore(teamTwoScore);
                if (teams.containsKey(teamOne.getName())){
                    teams.replace(teamOne.getName(), teams.get(teamOne.getName()) + teamOneScore);
                }
                else{
                    teams.put(teamOne.getName(), teamOneScore);
                }
                if (!teams.containsKey(teamTwo.getName())) {
                    teams.put(teamTwo.getName(), teamTwoScore);
                }
            }
            else if (currentGame.getScoreOne() == currentGame.getScoreTwo()){
               Team teamOne= currentGame.getTeamOne();
                int teamOneScore= 1;
                teamOne.setScore(teamOneScore);
                Team teamTwo= currentGame.getTeamTwo();
                int teamTwoScore= 1;
                teamTwo.setScore(teamTwoScore);
                if (teams.containsKey(teamOne.getName())){
                    teams.replace(teamOne.getName(), teams.get(teamOne.getName()) + teamOneScore);
                }
                else{
                    teams.put(teamOne.getName(), teamOneScore);
                }
                if (teams.containsKey(teamTwo.getName())){
                    teams.replace(teamTwo.getName(), teams.get(teamTwo.getName())+ teamTwoScore);
                }
                else{
                    teams.put(teamTwo.getName(), teamTwoScore);
                }
            }
            else{
                Team teamOne= currentGame.getTeamOne();
                Team teamTwo= currentGame.getTeamTwo();
                int teamOneScore= 0;
                int teamTwoScore= 3;
                teamOne.setScore(teamOneScore);
                teamTwo.setScore(teamTwoScore);
                if (!teams.containsKey(teamOne.getName())) {
                    teams.put(teamTwo.getName(), teamOneScore);
                }
                if (teams.containsKey(teamTwo.getName())){
                    teams.replace(teamTwo.getName(), teams.get(teamTwo.getName()) + teamTwoScore);
                }
                else{
                    teams.put(teamTwo.getName(), teamTwoScore);
                }
            }

        }
        sc.close();
        PrintTable(teams);

    }

    public static void PrintTable(TreeMap<String, Integer> teams){
        SortedSet sortedSet= entriesSortedByValues(teams);

        int position=1 ;
        int index= 0;
        int lastIndex= sortedSet.size() - 1;
        int count= 0;
        Iterator iterator= sortedSet.iterator();
        Iterator nextIterator= sortedSet.iterator();
        nextIterator.next();

        while(iterator.hasNext()){
            Map.Entry<String, Integer> stringIntegerEntry= (Map.Entry<String, Integer>) iterator.next();
            System.out.println(position
                    + ". "
                    + stringIntegerEntry.getKey()
                    + ", " + stringIntegerEntry.getValue()
                    + " pts");
            if(index<=lastIndex-1) {
                sortedSet.remove(stringIntegerEntry);
                Map.Entry<String, Integer> nextStringIntegerEntry= (Map.Entry<String, Integer>) nextIterator.next();
                if (stringIntegerEntry.getValue()!=nextStringIntegerEntry.getValue()) {
                    position= position + count + 1;
                }
                else{
                    count ++;
                }
            }
            index++;

        }
    }

    public static  <K,V extends Comparable<? super V>>
    SortedSet<Map.Entry<K,V>> entriesSortedByValues(Map<K,V> map) {
        SortedSet<Map.Entry<K,V>> sortedEntries = new TreeSet<Map.Entry<K,V>>(
                new Comparator<Map.Entry<K,V>>() {
                    @Override public int compare(Map.Entry<K,V> e1, Map.Entry<K,V> e2) {
                        int res = e2.getValue().compareTo(e1.getValue());
                        return res != 0 ? res : 1;
                    }
                }
        );
        sortedEntries.addAll(map.entrySet());
        return sortedEntries;
    }

}



