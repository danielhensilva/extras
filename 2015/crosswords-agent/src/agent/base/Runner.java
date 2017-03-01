package agent.base;

import java.util.*;

public abstract class Runner {

  public Runner() {
  }

  public abstract void execute();

  public void start() {
    this.stop();
    this.thread = new Thread(() -> {
      this.execute;
    });
    this.thread.start();
  }

  public void stop() {
    if (this.thread == null)
      return;

    this.thread.interrupt;
    this.thread = null;
  }

  private Thread thread;

}
