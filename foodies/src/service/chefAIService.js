import axios from "axios";

const API_URL = 'http://localhost:8080/api/ai';

export const fetchRecipe = async (dishName, token) => {
    try {
        const response = await axios.get(`${API_URL}/recipe`, {
            params: { dishName },
            headers: { Authorization: `Bearer ${token}` }
        });
        return response.data;
    } catch (error) {
        console.error('Error fetching recipe:', error);
        throw error;
    }
};