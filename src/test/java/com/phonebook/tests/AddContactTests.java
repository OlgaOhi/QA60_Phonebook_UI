
package com.phonebook.tests;

import com.phonebook.data.ContactData;
import com.phonebook.models.Contact;
import com.phonebook.models.User;
import com.phonebook.utils.DataProviders;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddContactTests extends TestBase {

    @BeforeMethod
    public void precondition() {

        if (!app.getUser().isLoginLinkPresent()) {
            app.getUser().clickOnSignOutButton();
        }

        app.getUser().clickOnLoginLink();
        app.getUser().fillRegisterLoginForm(new User().setEmail("olga22@gmail.com").setPassword("Aa12345!"));
        app.getUser().clickOnLoginButton();
    }

    @Test
    public void addContactPositiveTest() {

        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(ContactData.NAME)
                .setLastName(ContactData.LAST_NAME)
                .setPhone(ContactData.PHONE)
                .setEmail(ContactData.EMAIL)
                .setAddress(ContactData.ADDRESS)
                .setDescription(ContactData.DESCRIPTION));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactAdded(ContactData.NAME));

    }

    @Test(dataProvider = "addNewContact", dataProviderClass = DataProviders.class)
    public void addContactPositiveFromDataProviderTest(String name, String lastName,
                                                       String phone, String email,
                                                       String address, String description) {

        app.getContact().clickOnAddLink();
        app.getContact().fillContactForm(new Contact()
                .setName(name)
                .setLastName(lastName)
                .setPhone(phone)
                .setEmail(email)
                .setAddress(address)
                .setDescription(description));
        app.getContact().clickOnSaveButton();
        Assert.assertTrue(app.getContact().isContactAdded(name));

    }

        @Test(dataProvider = "addNewContactWithCsv", dataProviderClass = DataProviders.class)
        public void addContactPositiveFromDataProviderWithCsvTest (Contact contact){

            app.getContact().clickOnAddLink();
            app.getContact().fillContactForm(contact);
            app.getContact().clickOnSaveButton();
            Assert.assertTrue(app.getContact().isContactAdded(contact.getName()));

        }

        @AfterMethod
        public void postCondition () {
            app.getContact().deleteContact();
        }
    }
