import axios from 'axios';

const API_URL = 'http://localhost:8080/AEP_Backend';

export const registerUser = (userData) => {
    return axios.post(`${API_URL}/register`, userData, { withCredentials: true });
};

export const loginUser = (credentials) => {
    return axios.post(`${API_URL}/login`, credentials, { withCredentials: true });
};

export const createCourse = (courseData) => {
    return axios.post(`${API_URL}/course`, courseData, { withCredentials: true });
};

export const searchCourses = (params) => {
    return axios.get(`${API_URL}/search`, { params });
};

export const submitRequest = (requestData) => {
    return axios.post(`${API_URL}/request`, requestData, { withCredentials: true });
};

export const getNotifications = (professionalId) => {
    return axios.get(`${API_URL}/notifications`, { params: { professionalId } });
};

export const updateCourse = (courseData) => {
    return axios.put(`${API_URL}/course`, courseData, { withCredentials: true });
};

