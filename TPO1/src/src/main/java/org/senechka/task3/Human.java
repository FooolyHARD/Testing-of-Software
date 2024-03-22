package org.senechka.task3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public abstract class Human {
    final Sex sex;
    int age;
    final String name;

}
