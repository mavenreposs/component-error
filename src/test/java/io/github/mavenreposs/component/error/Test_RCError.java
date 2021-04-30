package io.github.mavenreposs.component.error;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class Test_RCError {

    @Test
    public void test_CreateError() {
        RCError error = new RCError();
        System.out.println(error);
    }

    @Test
    public void test_IsError() {
        Object error = new RCError();
        Object user = new User();
        System.out.println(error);
        System.out.println(RCError.is_error(error));
        System.out.println(RCError.is_error(user));

        assertTrue(RCError.is_error(error));
        assertFalse(RCError.is_error(user));
    }

    @Test
    public void test_CreateErrorWithArgs() {
        RCError error = new RCError(200, "OK");
        System.out.println(error);
    }

    @Test
    public void test_CreateErrorWithArgs02() {
        RCError error = new RCError(200, "OK");
        error.add(200, "OK2");
        error.add(200, "OK3");
        System.out.println(error);
    }

    @Test
    public void test_CreateErrorWithArgs03() {
        RCError error = new RCError(200, "OK");
        error.add(200, "OK2");
        error.add(200, "OK3");

        User user = new User();
        user.setName("Hello");
        user.setSex(1);

        error.add_data(user);
        System.out.println(error);
    }

    @Test
    public void test_CreateErrorWithArgs04() {
        RCError error = new RCError(200, "OK");
        error.add(200, "OK2");
        error.add(200, "OK3");
        error.add(300, "SS3");

        User user = new User();
        user.setName("Hello");
        user.setSex(1);

        error.add_data(user);
        System.out.println(error);
        System.out.println(error.get_error_codes());
        System.out.println(error.get_error_messages(200));
        System.out.println(error.get_error_message(200));
        System.out.println(error.get_error_data());
        System.out.println(error.get_error_data(300));
    }

    private static class User {
        private String name;
        private int sex;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", sex=" + sex +
                    '}';
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }
    }

}
