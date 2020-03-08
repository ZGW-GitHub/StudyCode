package demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author NotUpToYou
 * @date 2020/2/23 周日 15:18
 */
public class ComputerStore {

    private List<Object> pcs;

    private List<Object> notes;

    private Set<Object> set;

    public ComputerStore() {
        this.pcs = new ArrayList<>();
        this.notes = new ArrayList<>();
    }

    /**
     * 获取最老的PC
     * @return 最老的PC
     */
    public Object getPc() {
        if (pcs.isEmpty()) {
            return null;
        }
        return pcs.remove(0);
    }

    /**
     * 获取最老的Note
     * @return 最老的Note
     */
    public Object getNote() {
        if (notes.isEmpty()) {
            return null;
        }
        return notes.remove(0);
    }

    /**
     * 获取最老的Computer
     * @return 最老的Computer
     */
    public Object getComputer() {
        Object computer = getPc();
        if (computer == null) {
            return getNote();
        }
        return computer;
    }

    /**
     * 向仓库中存储Computer
     * @param obj Computer
     */
    public void setComputer(Object obj) {
        // 判断Computer是否是PC
        if (obj instanceof Object) {
            pcs.add(obj);
        } else {
            notes.add(obj);
        }

    }

}

