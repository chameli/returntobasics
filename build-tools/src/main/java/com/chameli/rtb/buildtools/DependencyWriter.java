package com.chameli.rtb.buildtools;

import java.io.PrintWriter;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DependencyWriter {

    private Set<ClassDefinition> printedClassDefinitions = new HashSet<>();
    private PrintWriter pw;

    public DependencyWriter(PrintWriter pw) {
        this.pw = pw;
    }

    private boolean isAllowed(String name) {
        return name.startsWith("net.java.cargotracker.");
    }

    public void writePlantuml(DependencyClassTreeNode root) {
        pw.println("@startuml");
        writeDefinitions(sortByName(getAllDistinctDefinitions(root)));
        writeRelations(root);
        pw.println("@enduml");
    }

    private List<ClassDefinition> sortByName(Set<ClassDefinition> definitions) {
        List<ClassDefinition> list = new ArrayList<>(definitions);
        list.sort(new Comparator<ClassDefinition>() {

            @Override
            public int compare(ClassDefinition o1, ClassDefinition o2) {
                return o1.getClassname().compareTo(o2.getClassname());
            }
        });
        return list;
    }

    private Set<ClassDefinition> getAllDistinctDefinitions(DependencyClassTreeNode node) {
        Set<ClassDefinition> s = new HashSet<>();
        getAllDistinctDefinitions(s, node);
        return s;
    }

    private void getAllDistinctDefinitions(Set<ClassDefinition> s, DependencyClassTreeNode node) {
        s.add(node.getDefinition());
        for (DependencyClassTreeNode child : node.getChildren()) {
            getAllDistinctDefinitions(s, child);
        }
    }

    private void writeRelations(DependencyClassTreeNode node) {
        if (!printedClassDefinitions.contains(node.getDefinition())) {
            printedClassDefinitions.add(node.getDefinition());
            for (DependencyClassTreeNode child : node.getChildren()) {
                if (!node.getDefinition().equals(child.getDefinition())) {
                    pw.println(node.getDefinition().getClassname() + " --> " + child.getDefinition().getClassname());
                }
            }
            for (DependencyClassTreeNode child : node.getChildren()) {
                if (!node.getDefinition().equals(child.getDefinition())) {
                    writeRelations(child);
                }
            }
        }
    }

    private void writeDefinitions(List<ClassDefinition> list) {
        for (ClassDefinition classDefinition : list) {
            if (isAllowed(classDefinition.getClassname())) {
                String identifier = "class";
                Class<?> clazz = getClazz(classDefinition.getClassname());
                if (isInterface(clazz)) {
                    identifier = "interface";
                } else if (isAbstract(clazz)) {
                    identifier = "abstract";
                } else if (isEnum(clazz)) {
                    identifier = "enum";
                }
                pw.println(identifier + " " + classDefinition.getClassname());
            }
        }
    }

    private boolean isEnum(Class<?> clazz) {
        return clazz.isEnum();
    }

    private boolean isInterface(Class<?> clazz) {
        return clazz.isInterface();
    }

    private boolean isAbstract(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers());
    }

    private Class<?> getClazz(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("For name " + name, e);
        }
    }

}
