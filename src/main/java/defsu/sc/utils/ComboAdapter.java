package defsu.sc.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public abstract class ComboAdapter implements Serializable {
    public abstract List<FKPair> getPairs();
}
