package com.senechka;

import org.senechka.task3.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestTask3 {
    @Nested
    class TestRoomAndPublics{
        private Place konura;
        private Place trashcan;
        private Place room;
        private Publics publics;
        private Someone someone;
        private PlaceHolder distPlace;

        @BeforeEach
        void setUp() throws Exception {
            konura = new Place(new Coordinates(10,10), PlaceType.KONURA);
            trashcan = new Place(new Coordinates(1,1), PlaceType.POMOIKA);
            room = new Place(new Coordinates(1,1), PlaceType.ROOM);
            publics = new Publics("People in room", 10, Moods.DEFAULT);
            someone = new Someone(Sex.MAN, 12, "Name", konura);
            distPlace = new PlaceHolder(publics, someone);
        }
        @Test
        @DisplayName("Check an existing of places")
        void checkPlaceDistanation() {
            Throwable exception = assertThrows(Exception.class, () -> someone.moveTo(trashcan));
            assertEquals("You can't move to this place", exception.getMessage());
        }

        @Test
        @DisplayName("Check an exising coordinates")
        void checkPlaceCoords() {
            Throwable exceptionplus = assertThrows(Exception.class, () -> room.setCoordinates(new Coordinates(100000,1000)));
            assertEquals("Unreachable coordinates", exceptionplus.getMessage());
            Throwable exceptionminus = assertThrows(Exception.class, () -> room.setCoordinates(new Coordinates(-100000,-1000)));
            assertEquals("Unreachable coordinates", exceptionminus.getMessage());
        }

        @Test
        @DisplayName("Check visible with normal shizlvl")
        void checkExtrimelvl() throws Exception {
            publics.shizanutsya();
            publics.shizanutsya();
            publics.shizanutsya();
            publics.shizanutsya();
            someone.moveTo(room);
            assertEquals("People in room feels SPIT", distPlace.checkArriving());

        }
        @Test
        @DisplayName("Check visible with normal shizlvl")
        void checkNormallvl() throws Exception {
            publics.shizanutsya();
            someone.moveTo(room);
            assertEquals("People in room feels DEFAULT", distPlace.checkArriving());
        }

        @Test
        @DisplayName("Check dead possible")
        void checkDead() {
            Throwable exception = assertThrows(Exception.class, () -> someone.grow(250));
            assertEquals("Name dead...", exception.getMessage());
        }

        @Test
        @DisplayName("Test growing")
        void checkGrowing(){
            someone.grow(10);
            someone.grow(10);
            someone.grow(10);
            someone.grow(10);
            assertEquals(52, someone.getAge());
        }

        @Test
        @DisplayName("Test mood setting")
        void checkMoodSet(){
            assertAll(
                    () -> {
                        someone.setMood(Moods.ANGRY);
                        assertEquals("ANGRY", someone.getMood().toString());
                    },
                    () -> {
                        someone.setMood(Moods.DEAD);
                        assertEquals("DEAD", someone.getMood().toString());
                    },
                    () -> {
                        publics.setMood(Moods.DEFAULT);
                        assertEquals("DEFAULT", publics.getMood().toString());
                    },
                    () -> {
                        publics.setMood(Moods.HAPPY);
                        assertEquals("HAPPY", publics.getMood().toString());
                    }
            );
        }
        @Test
        @DisplayName("Test max shiz lvl")
        void checkMaxShizLvl(){
            for (int i = 0; i < 6; i++) publics.shizanutsya();
            Throwable execption = assertThrows(Exception.class, () -> publics.shizanutsya());
            assertEquals("Reached max shiza lvl", execption.getMessage());
        }

        @Test
        @DisplayName("Check count of people in publics")
        void checkMaxCount(){
            assertAll(
                    () -> {publics.setCount(1);
                        assertEquals("Odin", publics.getAmount());
                    },
                    () -> {publics.setCount(10);
                        assertEquals("Mnogo", publics.getAmount());
                    }

            );
        }
    }
}
