package net.tinmints.scouting.model;

/**
 * Created by wpohlhau on 2/11/2017.
 */

public class ScoutDataFactory {

    private static ScoutData[] data = new ScoutData[3];
    private static ScoutDataFactory factory = new ScoutDataFactory();

    public static ScoutDataFactory instanceOf() {
        return factory;
    }

    private ScoutDataFactory() {
        for(int i=0;i<data.length;i++)
            data[i]=new ScoutData();
    }

    public ScoutData[] getData() {
        return data;
    }

    public ScoutData getData(int i) {
        if(i>=data.length)
            return null;
        return data[i];
    }

}
