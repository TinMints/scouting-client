package net.tinmints.scouting.model;

import java.io.Serializable;

/**
 * Created by wpohlhau on 2/8/2017.
 */

public class ScoutData implements Serializable {
    private String recorder;
    private int matchNumber;
    private String teamAlliance;
    private int teamNumber;
    private int teamStartPos;
    private int robotStartPos;
    private boolean autoMoved;
    private boolean autoCrossedLine;
    private boolean autoAttemptedPeg;
    private boolean autoMadePeg;
    private int autoPeg;
    private boolean autoAttemptedHighBoiler;
    private boolean autpAttemptedLowBoiler;
    private int autoHighBoiler;
    private int autoLowerBoiler;

    public int getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public String getTeamAlliance() {
        return teamAlliance;
    }

    public void setTeamAlliance(String teamAlliance) {
        this.teamAlliance = teamAlliance;
    }

    public int getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(int teamNumber) {
        this.teamNumber = teamNumber;
    }

    public int getTeamStartPos() {
        return teamStartPos;
    }

    public void setTeamStartPos(int teamStartPos) {
        this.teamStartPos = teamStartPos;
    }

    public int getRobotStartPos() {
        return robotStartPos;
    }

    public void setRobotStartPos(int robotStartPos) {
        this.robotStartPos = robotStartPos;
    }

    public boolean isAutoMoved() {
        return autoMoved;
    }

    public void setAutoMoved(boolean autoMoved) {
        this.autoMoved = autoMoved;
    }

    public boolean isAutoCrossedLine() {
        return autoCrossedLine;
    }

    public void setAutoCrossedLine(boolean autoCrossedLine) {
        this.autoCrossedLine = autoCrossedLine;
    }

    public boolean isAutoAttemptedPeg() {
        return autoAttemptedPeg;
    }

    public void setAutoAttemptedPeg(boolean autoAttemptedPeg) {
        this.autoAttemptedPeg = autoAttemptedPeg;
    }

    public boolean isAutoMadePeg() {
        return autoMadePeg;
    }

    public void setAutoMadePeg(boolean autoMadePeg) {
        this.autoMadePeg = autoMadePeg;
    }

    public int getAutoPeg() {
        return autoPeg;
    }

    public void setAutoPeg(int peg) {
        this.autoPeg = autoPeg;
    }

    public boolean isAutoAttemptedHighBoiler() {
        return autoAttemptedHighBoiler;
    }

    public void setAutoAttemptedHighBoiler(boolean autoAttemptedHighBoiler) {
        this.autoAttemptedHighBoiler = autoAttemptedHighBoiler;
    }

    public boolean isAutpAttemptedLowBoiler() {
        return autpAttemptedLowBoiler;
    }

    public void setAutpAttemptedLowBoiler(boolean autpAttemptedLowBoiler) {
        this.autpAttemptedLowBoiler = autpAttemptedLowBoiler;
    }

    public int getAutoHighBoiler() {
        return autoHighBoiler;
    }

    public void setAutoHighBoiler(int autoHighBoiler) {
        this.autoHighBoiler = autoHighBoiler;
    }

    public int getAutoLowerBoiler() {
        return autoLowerBoiler;
    }

    public void setAutoLowerBoiler(int autoLowerBoiler) {
        this.autoLowerBoiler = autoLowerBoiler;
    }

    public String getRecorder() {
        return recorder;
    }

    public void setRecorder(String recorder) {
        this.recorder = recorder;
    }
}
