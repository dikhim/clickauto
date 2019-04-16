package org.dikhim.clickauto.jsengine.objects;

import org.dikhim.clickauto.jsengine.ClickAutoScriptEngine;
import org.dikhim.clickauto.jsengine.objects.Classes.Image;
import org.dikhim.clickauto.util.MathUtil;
import org.dikhim.clickauto.util.logger.Log;
import org.dikhim.clickauto.jsengine.robot.Robot;

public class ScriptSystemObject implements SystemObject {
    protected ClickAutoScriptEngine engine;
    protected final Robot robot;
    protected final double MULTIPLIER = 1;

    protected double multiplier = MULTIPLIER;

    public ScriptSystemObject(ClickAutoScriptEngine engine) {
        this.engine = engine;
        this.robot = engine.getRobot();
    }

    @Override
    public int getMultipliedDelay(int ms) {
        synchronized (robot) {
            int result = ((int) (ms * multiplier));
            if (result <= 0) {
                return 0;
            } else {
                return result;
            }
        }
    }

    @Override
    public double getMultiplier() {
        synchronized (robot) {
            return multiplier;
        }
    }

    @Override
    public double getSpeed() {
        synchronized (robot) {
            if(multiplier==0) return 999999999;
            return MathUtil.roundTo1(1.0 / multiplier);
        }
    }



    @Override
    public void print(String s) {
        synchronized (robot) {
            Log.out(s);
        }
    }

    @Override
    public void println() {
        synchronized (robot) {
            Log.out("\n");
        }
    }

    @Override
    public void println(String s) {
        synchronized (robot) {
            Log.out(s + "\n");
        }
    }

    @Override
    public void setMaxThreads(String name, int maxThreads) {
        engine.registerInvocableMethod(name, maxThreads);
    }

    @Override
    public void resetMultiplier() {
        synchronized (robot) {
            this.multiplier = MULTIPLIER;
        }
    }

    @Override
    public void resetSpeed() {
        synchronized (robot) {
            this.multiplier = MULTIPLIER;
        }
    }

    @Override
    public void setMultiplier(double multiplier) {
        synchronized (robot) {
            if (multiplier < 0) {
                this.multiplier = 0;
            } else {
                this.multiplier = multiplier;
            }
        }
    }

    @Override
    public void sleep(int ms) {
        if (ms <= 0) return;
        try {
            Thread.sleep(getMultipliedDelay(ms));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void sleepNonMultiplied(int ms) {
        if (ms <= 0) return;
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Override
    public void setSpeed(double speed) {
        synchronized (robot) {
            if (speed < 0.1) {
                speed = 0.1;
            }
            speed = MathUtil.roundTo1(speed);
            setMultiplier(1f / speed);
        }
    }

    @Override
    public void showImage(Image image) {
        synchronized (robot) {
            // TODO
        }
    }
}
