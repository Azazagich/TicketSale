package com.study;
import com.study.domain.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    private static final Logger LOGGER = LogManager.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Integer integer = 56165;
        Double integer1 = 5426.0116;



        String string = "56165";
        String string1 = "5426";

        System.out.println(string + string1);
        System.out.println(integer + integer1);
    }
}