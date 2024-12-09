import React, { useState } from 'react';
import { searchCourses, submitRequest } from '../api/api';
import { Container, TextField, Button, Select, MenuItem, Typography, Box, Card, CardContent, CardActions } from '@mui/material';

const SearchCourses = () => {
    const [filters, setFilters] = useState({
        courseCode: '',
        courseTitle: '',
        institutionName: '',
        term: '',
        schedule: '',
        deliveryMethod: ''
    });
    const [courses, setCourses] = useState([]);

    const handleChange = (e) => {
        setFilters({ ...filters, [e.target.name]: e.target.value });
    };

    const handleSearch = () => {
        searchCourses(filters)
            .then(response => {
                setCourses(response.data);
            })
            .catch(() => {
                alert('Search Failed');
            });
    };

    const handleRequest = (courseId, professionalId) => {
        submitRequest({ action: 'submit', courseId, professionalId })
            .then(() => {
                alert('Request Submitted');
            })
            .catch(() => {
                alert('Submission Failed');
            });
    };

    return (
        <Container maxWidth="md">
            <Box sx={{ mt: 5, mb: 3 }}>
                <Typography variant="h5" gutterBottom>Search Courses</Typography>
                <Box sx={{ display: 'flex', flexWrap: 'wrap', gap: 2 }}>
                    <TextField
                        label="Course Code"
                        name="courseCode"
                        value={filters.courseCode}
                        onChange={handleChange}
                        sx={{ flex: 1 }}
                    />
                    <TextField
                        label="Course Title"
                        name="courseTitle"
                        value={filters.courseTitle}
                        onChange={handleChange}
                        sx={{ flex: 2 }}
                    />
                    <TextField
                        label="Institution Name"
                        name="institutionName"
                        value={filters.institutionName}
                        onChange={handleChange}
                        sx={{ flex: 2 }}
                    />
                    <Select
                        name="term"
                        value={filters.term}
                        onChange={handleChange}
                        displayEmpty
                        sx={{ flex: 1 }}
                    >
                        <MenuItem value=""><em>Term</em></MenuItem>
                        <MenuItem value="24F">24F</MenuItem>
                        <MenuItem value="24S">24S</MenuItem>
                        <MenuItem value="24W">24W</MenuItem>
                    </Select>
                    <Select
                        name="schedule"
                        value={filters.schedule}
                        onChange={handleChange}
                        displayEmpty
                        sx={{ flex: 1 }}
                    >
                        <MenuItem value=""><em>Schedule</em></MenuItem>
                        <MenuItem value="Morning">Morning</MenuItem>
                        <MenuItem value="Afternoon">Afternoon</MenuItem>
                        <MenuItem value="Evening">Evening</MenuItem>
                    </Select>
                    <Select
                        name="deliveryMethod"
                        value={filters.deliveryMethod}
                        onChange={handleChange}
                        displayEmpty
                        sx={{ flex: 1 }}
                    >
                        <MenuItem value=""><em>Delivery Method</em></MenuItem>
                        <MenuItem value="In-Person">In-Person</MenuItem>
                        <MenuItem value="Remote">Remote</MenuItem>
                        <MenuItem value="Hybrid">Hybrid</MenuItem>
                    </Select>
                </Box>
                <Button variant="contained" color="primary" onClick={handleSearch} sx={{ mt: 2 }}>
                    Search
                </Button>
            </Box>
            <Box sx={{ display: 'flex', flexDirection: 'column', gap: 2 }}>
                {courses.map(course => (
                    <Card key={course.courseId}>
                        <CardContent>
                            <Typography variant="h6">{course.courseTitle} ({course.courseCode})</Typography>
                            <Typography variant="body2">{course.outline}</Typography>
                            <Typography variant="body2">Institution: {course.institution_name}</Typography>
                            <Typography variant="body2">Term: {course.term}</Typography>
                            <Typography variant="body2">Schedule: {course.schedule}</Typography>
                            <Typography variant="body2">Delivery Method: {course.delivery_method}</Typography>
                            <Typography variant="body2">Compensation: ${course.compensation}</Typography>
                        </CardContent>
                        <CardActions>
                            <Button size="small" variant="contained" color="secondary" onClick={() => handleRequest(course.courseId, 1)}>
                                Request to Teach
                            </Button>
                        </CardActions>
                    </Card>
                ))}
            </Box>
        </Container>
    );
};

export default SearchCourses;

