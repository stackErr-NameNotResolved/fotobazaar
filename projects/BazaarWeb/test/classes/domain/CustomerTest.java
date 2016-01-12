package classes.domain;

import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.core.Is.is;

public class CustomerTest {

    @Test
    public void testFromId() throws Exception {
        Customer customer = Customer.fromId(1);

        Assert.assertThat(customer, notNullValue());
        Assert.assertThat(customer.getId(), is(1));
        Assert.assertThat(customer.getInitials(), is(not("")));
    }

    @Test
    public void testInsert() {
        // Insert.
        Customer customer = new Customer("T", "Lastname", 0, "teststreet", "2", "6371TR", "Eindhoven", "unit@test.nl", 1, "Nederland");
        customer.insert();

        // Find.
        Customer find = Customer.fromId(customer.getId());
        Assert.assertThat(find, notNullValue());
        Assert.assertThat(find.getId(), is(not(0)));

        // Delete.
        int id = find.getId();
        Assert.assertThat(find.delete(), is(true));

        // Confirm deleted.
        find = Customer.fromId(id);
        Assert.assertThat(find, nullValue());
    }
}
