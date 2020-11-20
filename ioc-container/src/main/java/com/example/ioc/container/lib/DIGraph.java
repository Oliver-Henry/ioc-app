package com.example.ioc.container.lib;

import com.example.ioc.container.annotations.Inject;
import com.example.ioc.container.exception.DIGraphException;
import com.example.ioc.container.util.Utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class DIGraph {
    protected static DIGraph INSTANCE;

    private final Set<Object> serviceInstances = new HashSet<>();
    private final Set<Class<?>> dependencies = new HashSet<>();

    public static DIGraph getContainer() {
        if(INSTANCE == null) {
            INSTANCE = new DIGraph();
        }
        return INSTANCE;
    }

    private DIGraph() {
        // Avoid accidental instantiation
    }

    public void generateGraph() throws DIGraphException {
        if(dependencies.size() == 0) {
            throw new DIGraphException(Utils.EXPECTED_FORGOT_MESSAGE);
        }
        try {
            for (Class<?> serviceClass : dependencies) {
                Constructor<?> constructor = serviceClass.getConstructor();
                constructor.setAccessible(true);
                Object serviceInstance = constructor.newInstance();
                this.serviceInstances.add(serviceInstance);
            }

            for (Object serviceInstance : this.serviceInstances) {
                for (Field field : serviceInstance.getClass().getDeclaredFields()) {
                    if (!field.isAnnotationPresent(Inject.class)) {
                        continue;
                    }

                    Class<?> fieldType = field.getType();
                    field.setAccessible(true);

                    for (Object matchPartner : this.serviceInstances) {
                        if (fieldType.isInstance(matchPartner)) {
                            field.set(serviceInstance, matchPartner);
                        }
                    }
                }
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            throw new DIGraphException(Utils.EXPECTED_MISSING_MESSAGE);
        } catch (InstantiationException e) {
            e.printStackTrace();
            throw new DIGraphException(Utils.EXPECTED_INSTANTIATION_MESSAGE);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            throw new DIGraphException(Utils.EXPECTED_INVOCATION_MESSAGE);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new DIGraphException(Utils.EXPECTED_SETTING_MESSAGE);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T inject(Class<T> serviceClass) {
        for(Object serviceInstance : this.serviceInstances){
            if(serviceClass.isInstance(serviceInstance)){
                return (T)serviceInstance;
            }
        }
        return null;
    }

    public void installDependency(Class<?> dependency) {
        dependencies.add(dependency);
    }

}
