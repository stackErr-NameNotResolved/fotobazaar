package classes.domain;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CustomerTest {

    @Test
    public void testFromId() throws Exception {
        Customer customer = Customer.fromId(1);

        assertThat(customer, notNullValue());
        assertThat(customer.getId(), is(1));
        assertThat(customer.getInitials(), is(not("")));
    }

    @Test
    public void testInsert() {
        // Insert.
        Customer customer = new Customer("Testing", "Lastname", 0, "teststreet", "2", "6371TR", "Eindhoven", "unit@test.nl", 1);
        customer.insert();

        // Find.
        Customer find = Customer.fromId(customer.getId());
        assertThat(find, notNullValue());
        assertThat(find.getId(), is(not(0)));

        // Delete.
        int id = find.getId();
        assertThat(find.delete(), is(true));

        // Confirm deleted.
        find = Customer.fromId(id);
        assertThat(find, nullValue());
    }
}
