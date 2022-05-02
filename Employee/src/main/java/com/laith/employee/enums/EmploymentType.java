package com.laith.employee.enums;

public enum EmploymentType {
    PERMANENT, INTERN, CONTRACT;

    static public EmploymentType forTypeIgnoreCase(String value) {
        for (EmploymentType type : EmploymentType.values()) {
            if (type.name().equalsIgnoreCase(value)) return type;
        }
        return null;
    }
}
