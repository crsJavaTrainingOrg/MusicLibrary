package hu.adam.sipos.music.serialization;

public interface SerializationService {
    String serialize(Object object);

    Object deserialize(String serializedObject);
}
