/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Repertoire.Shared.Sql;

/**
 *
 * @author rndmorris
 */
public final class SqlParameter {
    private SqlType type;
    private Object value;
    private int index;

    public SqlParameter()
    {}
    public SqlParameter(SqlType type, Object value, int index)
    {
        setType(type);
        setValue(value);
        setIndex(index);
    }

    /**
     * @return the type
     */
    public SqlType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(SqlType type) {
        this.type = type;
    }

    /**
     * @return the value
     */
    public Object getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(Object value) {
        this.value = value;
    }

    /**
     * Begins at index = 1
     * @return the position
     */
    public int getIndex() {
        return index;
    }

    /**
     * Begins at index = 1
     * @param index the position to set
     */
    public void setIndex(int index) {
        if (index <= 0 )
        {
            throw new IllegalArgumentException("index cannot be less than 1.");
        }
        this.index = index;
    }
    
}
