package Task9.TestBase;

import org.junit.Before;

public class TestBase {

   public Application app;

    @Before
    public void Initialization() {
        app = new Application();
    }
}
