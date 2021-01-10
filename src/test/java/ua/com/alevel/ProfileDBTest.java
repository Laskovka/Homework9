package ua.com.alevel;

import org.apache.commons.collections.CollectionUtils;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import ua.com.alevel.db.impl.ProfileDBImpl;
import ua.com.alevel.entity.Profile;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ProfileDBTest implements AbstractCrudTest{
    @BeforeAll
    public static void init() {
        for (int i = 0; i < 10; i++) {
            int age = ThreadLocalRandom.current().nextInt(18, 25 + 1);
            String name = "Test" + i;
            String surname = "Test" + i;
            String email = "test" + i + "@gmail.com";
            String gender = i % 2 == 0 ? "Woman" : "Man";
            Profile profile = new Profile();
            profile.setName(name);
            profile.setSurname(surname);
            profile.setEmail(email);
            profile.setGender(gender);
            profile.setAge(age);
            ProfileDBImpl.getInstance().create(profile);
        }
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 10);
    }

    @Override
    @Order(1)
    public void create() {
        Profile profile = new Profile();
        profile.setName("TestName");
        ProfileDBImpl.getInstance().create(profile);
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 11);
    }

    @Override
    @Order(2)
    public void read() {
        List<Profile> profiles = ProfileDBImpl.getInstance().readAll();
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 11);
        profiles = ProfileDBImpl.getInstance().read("id", 1);
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
    }

    @Override
    @Order(3)
    public void update() {
        List<Profile> profiles = ProfileDBImpl.getInstance().read("email", "test2@gmail.com");
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
        Profile profile = profiles.get(0);
        profile.setEmail("test22@gmail.com");
        ProfileDBImpl.getInstance().update(profile);
        profiles = ProfileDBImpl.getInstance().read("email", "test22@gmail.com");
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
    }

    @Override
    @Order(4)
    public void delete() {
        List<Profile> profiles = ProfileDBImpl.getInstance().read("email", "test22@gmail.com");
        Assert.assertTrue(CollectionUtils.isNotEmpty(profiles));
        Assert.assertEquals(profiles.size(), 1);
        ProfileDBImpl.getInstance().delete(profiles.get(0).getId());
        profiles = ProfileDBImpl.getInstance().read("email", "test22@gmail.com");
        Assert.assertTrue(CollectionUtils.isEmpty(profiles));
    }
}
