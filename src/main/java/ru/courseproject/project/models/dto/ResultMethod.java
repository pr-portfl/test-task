package ru.courseproject.project.models.dto;


public final class ResultMethod {

    public final boolean RESULT;
    public final String MESSAGE;

    public final Object OBJECT;

    public ResultMethod(boolean res, String mes, Object obj) {
        RESULT = res;
        MESSAGE = mes.equalsIgnoreCase("ok") ? "ok" : mes;
        this.OBJECT = obj;
    }

    public ResultMethod(Object obj) {
        RESULT = true;
        MESSAGE = "ok";
        OBJECT = obj;
    }

    public ResultMethod(String mes) {
        RESULT = false;
        MESSAGE = mes;
        OBJECT = null;
    }

}
