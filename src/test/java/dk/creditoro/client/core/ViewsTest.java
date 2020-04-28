package dk.creditoro.client.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ViewsTest {


    @Test
    public void testEnumValues() {
        for (Views view : Views.values()){
            assertTrue(isView(view));
        }
    }

    public boolean isView(Views view) {
        for (Views vie : Views.values()){
            if (vie.equals(view)){
                return true;
            }
        }
        return false;
    }
}