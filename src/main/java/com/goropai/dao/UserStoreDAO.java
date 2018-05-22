package com.goropai.dao;

public interface UserStoreDAO {

    /**
     * Method inserts mark into student's course
     * @param courseId course's id
     * @param userId user's id
     * @param mark mark
     * @return true if insertion was successful, false if it was not
     */
    boolean insertMark(Long courseId, Long userId, Integer mark);

    /**
     * Method inserts student into course
     * @param courseId course,s id
     * @param userId user,s id
     * @return true if insertion was successful, false if it was not
     */
    boolean insertStudentIntoCourse(Long courseId, Long userId);

    /**
     * Method finds mark by course's id and user's id
     * @param courseId course's id
     * @param userId user's id
     * @return mark
     */
    Integer findMark(Long courseId, Long userId);

    /**
     * Method removes student from course
     * @param courseId course's id
     * @param userId user's id
     * @return true if deletion was successful, false if it was not
     */
    boolean removeStudent(Long courseId, Long userId);

    //todo remove
    /**
     * Method removes course from user by its id
     * @param courseId course's id
     * @return true if removing was successful, false if it was not
     */
    boolean removeCourse(Long courseId);
}
