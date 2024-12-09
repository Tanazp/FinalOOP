import React, { useState } from 'react';
import { registerUser } from '../api/api';
import { useNavigate } from 'react-router-dom';
import { Container, TextField, Button, Select, MenuItem, Typography, Box, FormControl, InputLabel } from '@mui/material';

const Register = () => {
    const [userType, setUserType] = useState('Professional');
    const [formData, setFormData] = useState({
        name: '',
        email: '',
        password: '',
        currentInstitution: '',
        academicPosition: '',
        educationBackground: '',
        expertiseArea: '',
        institutionName: '',
        address: '',
        coursesOffered: ''
    });
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({ ...formData, [e.target.name]: e.target.value });
    };

    const handleSubmit = (e) => {
        e.preventDefault();
        const data = { userType, ...formData };
        registerUser(data)
            .then(() => {
                navigate('/login');
            })
            .catch(() => {
                alert('Registration Failed');
            });
    };

    return (
        <Container maxWidth="sm">
            <Box sx={{ mt: 5 }}>
                <Typography variant="h4" gutterBottom>Register</Typography>
                <form onSubmit={handleSubmit}>
                    <FormControl fullWidth sx={{ mb: 2 }}>
                        <InputLabel>User Type</InputLabel>
                        <Select
                            value={userType}
                            label="User Type"
                            onChange={(e) => setUserType(e.target.value)}
                        >
                            <MenuItem value="Professional">Academic Professional</MenuItem>
                            <MenuItem value="Institution">Academic Institution</MenuItem>
                        </Select>
                    </FormControl>
                    {userType === 'Professional' && (
                        <>
                            <TextField
                                label="Name"
                                name="name"
                                value={formData.name}
                                onChange={handleChange}
                                fullWidth
                                required
                                sx={{ mb: 2 }}
                            />
                            <TextField
                                label="Current Institution"
                                name="currentInstitution"
                                value={formData.currentInstitution}
                                onChange={handleChange}
                                fullWidth
                                required
                                sx={{ mb: 2 }}
                            />
                            <TextField
                                label="Academic Position"
                                name="academicPosition"
                                value={formData.academicPosition}
                                onChange={handleChange}
                                fullWidth
                                required
                                sx={{ mb: 2 }}
                            />
                            <TextField
                                label="Education Background"
                                name="educationBackground"
                                value={formData.educationBackground}
                                onChange={handleChange}
                                fullWidth
                                required
                                multiline
                                rows={3}
                                sx={{ mb: 2 }}
                            />
                            <TextField
                                label="Expertise Area"
                                name="expertiseArea"
                                value={formData.expertiseArea}
                                onChange={handleChange}
                                fullWidth
                                required
                                sx={{ mb: 2 }}
                            />
                        </>
                    )}
                    {userType === 'Institution' && (
                        <>
                            <FormControl fullWidth sx={{ mb: 2 }}>
                                <InputLabel>Institution Name</InputLabel>
                                <Select
                                    name="institutionName"
                                    value={formData.institutionName}
                                    label="Institution Name"
                                    onChange={handleChange}
                                    required
                                >
                                    <MenuItem value="Algonquin College">Algonquin College</MenuItem>
                                    <MenuItem value="University A">University A</MenuItem>
                                    <MenuItem value="Institute B">Institute B</MenuItem>
                                </Select>
                            </FormControl>
                            <TextField
                                label="Address"
                                name="address"
                                value={formData.address}
                                onChange={handleChange}
                                fullWidth
                                required
                                multiline
                                rows={3}
                                sx={{ mb: 2 }}
                            />
                            <TextField
                                label="Courses Offered"
                                name="coursesOffered"
                                value={formData.coursesOffered}
                                onChange={handleChange}
                                fullWidth
                                required
                                sx={{ mb: 2 }}
                            />
                        </>
                    )}
                    <TextField
                        label="Email"
                        name="email"
                        type="email"
                        value={formData.email}
                        onChange={handleChange}
                        fullWidth
                        required
                        sx={{ mb: 2 }}
                    />
                    <TextField
                        label="Password"
                        name="password"
                        type="password"
                        value={formData.password}
                        onChange={handleChange}
                        fullWidth
                        required
                        sx={{ mb: 2 }}
                    />
                    <Button variant="contained" color="primary" type="submit" fullWidth>
                        Register
                    </Button>
                </form>
            </Box>
        </Container>
    );
};

export default Register;

