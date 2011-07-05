package com.lm.common.proxy;

class GreetImpl implements Greet
{
    public void sayHello(String name)
    {
        System.out.println("Hello " + name);
    }

    public void goodBye()
    {
        System.out.println("Good bye.");
    }
}

