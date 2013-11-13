/**
 * 
 */
package fr.wati.school.services.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.util.ReflectionUtils;
import org.springframework.util.ReflectionUtils.FieldCallback;

import fr.wati.school.entities.annotations.ViewCaption;
import fr.wati.school.entities.bean.Etablissement;
import fr.wati.school.entities.bean.Etudiant;

/**
 * @author Rachid Ouattara
 * 
 */
public class AnnotationUtils {

	private static List<Class<? extends Annotation>> nonJPAClassForCreation = new ArrayList<>();

	static {
		nonJPAClassForCreation.add(ManyToMany.class);
		nonJPAClassForCreation.add(OneToMany.class);
		nonJPAClassForCreation.add(Id.class);
	}

	public static List<Field> getNonJPAFieldsForCreation(Class<?> clazz) {
		final List<Field> fields = new ArrayList<>();
		ReflectionUtils.doWithFields(clazz, new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				if (!hasAnyOfAnnotation(field, nonJPAClassForCreation)) {
					fields.add(field);
				}
			}
		});
		return fields;
	}

	public static List<String> getNonJPAPropertiesForCreation(Class<?> clazz) {
		final List<String> fieldsNames = new ArrayList<>();
		ReflectionUtils.doWithFields(clazz, new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				if (!hasAnyOfAnnotation(field, nonJPAClassForCreation)) {
					fieldsNames.add(field.getName());
				}
			}
		});
		return fieldsNames;
	}

	public static boolean hasAnyOfAnnotation(Field field,
			Collection<Class<? extends Annotation>> annotationClasses) {
		for (Class<? extends Annotation> currentAnnotation : annotationClasses) {
			if (field.getAnnotation(currentAnnotation) != null) {
				return true;
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	public static boolean hasAnyOfAnnotation(Class<?> targetClass,
			Class<? extends Annotation>... annotationClasses) {
		for (Class<? extends Annotation> currentAnnotation : annotationClasses) {
			if (targetClass.getAnnotation(currentAnnotation) != null) {
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args) {
		System.out.println(getNonJPAPropertiesForCreation(Etudiant.class));
		System.out.println(getFirstPropertyNameWithAnnotation(
				ViewCaption.class, Etablissement.class));
	}

	public static String getFirstPropertyNameWithAnnotation(
			final Class<? extends Annotation> annotationClass,
			Class<?> targetClass) {
		final List<String> properties = new ArrayList<>();
		ReflectionUtils.doWithFields(targetClass, new FieldCallback() {
			@Override
			public void doWith(Field field) throws IllegalArgumentException,
					IllegalAccessException {
				List<Class<? extends Annotation>> annotations = new ArrayList<>();
				annotations.add(annotationClass);
				if (hasAnyOfAnnotation(field, annotations)) {
					properties.add(field.getName());
				}
			}
		});
		return !properties.isEmpty() ? properties.get(0) : null;
	}
}
