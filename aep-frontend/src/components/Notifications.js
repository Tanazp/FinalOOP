import React, { useState, useEffect } from 'react';
import { getNotifications } from '../api/api';
import { Container, Typography, Box, Card, CardContent, Checkbox, FormControlLabel } from '@mui/material';

const Notifications = () => {
    const [notifications, setNotifications] = useState([]);

    useEffect(() => {
        const professionalId = 1;
        getNotifications(professionalId)
            .then(response => {
                setNotifications(response.data);
            })
            .catch(() => {
                alert('Failed to fetch notifications');
            });
    }, []);

    return (
        <Container maxWidth="md">
            <Box sx={{ mt: 5 }}>
                <Typography variant="h5" gutterBottom>Notifications</Typography>
                {notifications.map(notification => (
                    <Card key={notification.notificationId} sx={{ mb: 2, backgroundColor: notification.is_read ? '#f0f0f0' : '#ffffff' }}>
                        <CardContent>
                            <Typography variant="body1">{notification.message}</Typography>
                            <Typography variant="caption" color="text.secondary">{notification.createdAt}</Typography>
                            <FormControlLabel
                                control={<Checkbox checked={notification.is_read} />}
                                label="Read"
                                disabled
                                sx={{ ml: 2 }}
                            />
                        </CardContent>
                    </Card>
                ))}
            </Box>
        </Container>
    );
};

export default Notifications;

