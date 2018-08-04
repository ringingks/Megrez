package com.Y3.AnalyticsTeam.CT.Cancer.DTO;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public class SysRuleItemSet {

    private String elementcode;

    private String valueset;

    private int expression;

    private int isnum;

    public String getElementcode() {
        return elementcode;
    }

    public void setElementcode(String elementcode) {
        this.elementcode = elementcode;
    }

    public String getValueset() {
        return valueset;
    }

    public void setValueset(String valueset) {
        this.valueset = valueset;
    }

    public int getExpression() {
        return expression;
    }

    public void setExpression(int expression) {
        this.expression = expression;
    }

    public int getIsnum() {
        return isnum;
    }

    public void setIsnum(int isnum) {
        this.isnum = isnum;
    }
}
