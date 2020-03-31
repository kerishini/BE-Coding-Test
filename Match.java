public class Match {
    Team teamOne;
    int scoreOne;
    Team teamTwo;
    int scoreTwo;

    public Match(){
    }

    public Match(Team teamOne, int scoreOne, Team teamTwo, int scoreTwo){
        this.teamOne= teamOne;
        this.scoreOne= scoreOne;
        this.teamTwo= teamTwo;
        this.scoreTwo= scoreTwo;
    }

    public int getScoreOne() {
        return scoreOne;
    }

    public void setScoreOne(int scoreOne) {
        this.scoreOne = scoreOne;
    }

    public Team getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(Team teamTwo) {
        this.teamTwo = teamTwo;
    }

    public int getScoreTwo() {
        return scoreTwo;
    }

    public void setScoreTwo(int scoreTwo) {
        this.scoreTwo = scoreTwo;
    }

    public Team getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(Team teamOne) {
        this.teamOne = teamOne;
    }

}
