import React, { useState, useEffect } from 'react';
import { updateCourse, searchCourses } from '../api/api';
import { useNavigate, useParams } from 'react-router-dom';
import { Container, TextField, Button, Select, MenuItem, Typography, Box, FormControl, InputLabel } from '@mui/material';

const EditCourse = () => {
    const [courseData, setCourseData] = useState({
        courseId: '',
        courseTitle: '',
        courseCode: '',
        term: '',
        outline: '',
        schedule: '',
        deliveryMethod: '',
        compensation: '',
        preferredQualifications: ''
    });
    const navigate = useNavigate();
    const { id } = useParams();

    useEffect(() => {
        const fetchCourse = async () => {
            const response = await searchCourses({ courseId: id });
            const course = response.data.find(c => c.courseId === parseInt(id));
            if(course){
                setCourseData({
                    courseId: course.courseId,
                    courseTitle: course.courseTitle,
                    courseCode: course.courseCode,
                    term: course.term,
                    outline: course.outline,
                    schedule: course.schedule,
                    deliveryMethod: course.deliveryMethod,
                    compensation: course.compensation,
                    preferredQualifications: course.preferredQualifications
                });
            }
        };
        fetchCourse();
    }, [id]);

    const handleChange = (e) => {
        setCourseData({...courseData, [e.target.name]: e.target.value});
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = { action: 'update', ...courseData };
        updateCourse(data)
            .then(() => {
                navigate('/dashboard');
            })
            .catch(() => {
                alert('Course Update Failed');
            });
    };

    return (
        <Container maxWidth="sm">
            <Box sx={{ mt: 5 }}>
                <Typography variant="h5" gutterBottom>Edit Course</Typography>
                <form onSubmit={handleSubmit}>
                    <TextField
                        label="Course Title"
                        name="courseTitle"
                        value={courseData.courseTitle}
                        onChange={handleChange}
                        fullWidth
                        required
                        sx={{ mb: 2 }}
                    />
                    <TextField
                        label="Course Code"
                        name="courseCode"
                        value={courseData.courseCode}
                        onChange={handleChange}
                        fullWidth
                        required
                        sx={{ mb: 2 }}
                    />
                    <FormControl fullWidth sx={{ mb: 2 }}>
                        <InputLabel>Term</InputLabel>
                        <Select
                            name="term"
                            value={courseData.term}
                            label="Term"
                            onChange={handleChange}
                        >
                            <MenuItem value="24F">24F</MenuItem>
                            <MenuItem value="24S">24S</MenuItem>
                            <MenuItem value="24W">24W</MenuItem>
                        </Select>
                    </FormControl>
                    <TextField
                        label="Course Outline"
                        name="outline"
                        value={courseData.outline}
                        onChange={handleChange}
                        fullWidth
                        required
                        multiline
                        rows={4}
                        sx={{ mb: 2 }}
                    />
                    <FormControl fullWidth sx={{ mb: 2 }}>
                        <InputLabel>Schedule</InputLabel>
                        <Select
                            name="schedule"
                            value={courseData.schedule}
                            label="Schedule"
                            onChange={handleChange}
                        >
                            <MenuItem value="Morning">Morning</MenuItem>
                            <MenuItem value="Afternoon">Afternoon</MenuItem>
                            <MenuItem value="Evening">Evening</MenuItem>
                        </Select>
                    </FormControl>
                    <FormControl fullWidth sx={{ mb: 2 }}>
                        <InputLabel>Delivery Method</InputLabel>
                        <Select
                            name="deliveryMethod"
                            value={courseData.deliveryMethod}
                            label="Delivery Method"
                            onChange={handleChange}
                        >
                            <MenuItem value="In-Person">In-Person</MenuItem>
                            <MenuItem value="Remote">Remote</MenuItem>
                            <MenuItem value="Hybrid">Hybrid</MenuItem>
                        </Select>
                    </FormControl>
                    <TextField
                        label="Compensation"
                        name="compensation"
                        type="number"
                        value={courseData.compensation}
                        onChange={handleChange}
                        fullWidth
                        required
                        sx={{ mb: 2 }}
                    />
                    <TextField
                        label="Preferred Qualifications"
                        name="preferredQualifications"
                        value={courseData.preferredQualifications}
                        onChange={handleChange}
                        fullWidth
                        required
                        multiline
                        rows={3}
                        sx={{ mb: 2 }}
                    />
                    <Button variant="contained" color="primary" type="submit" fullWidth>
                        Update Course
                    </Button>
                </form>
            </Box>
        </Container>
    );
};

export default EditCourse;

