package defsu.sc.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import defsu.sc.utils.ComboAdapter;
import defsu.sc.utils.FKPair;

import java.util.ArrayList;
import java.util.List;


public class SelectBox extends ComboAdapter {


    public List<FKPair> pairs_ = new ArrayList<>();

    public SelectBox(String pairs){
       String[] p = pairs.split("&&");
       for(String s : p){
           String[] ss = s.split("::");
           pairs_.add(new FKPair(Long.parseLong(ss[0]), ss[1]));
       }
    }

    @JsonProperty("pairs")
    @Override
    public List<FKPair> getPairs() {
        return getPairs_();
    }

    @JsonIgnore
    public List<FKPair> getPairs_() {
        return pairs_;
    }

    public void setPairs_(List<FKPair> pairs_) {
        this.pairs_ = pairs_;
    }
}
