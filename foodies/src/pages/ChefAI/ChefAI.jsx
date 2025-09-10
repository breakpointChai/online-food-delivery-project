import React, { useState, useContext } from 'react'; // Import useContext
import { fetchRecipe } from '../../service/chefAIService';
import { StoreContext } from '../../context/StoreContext'; // Import StoreContext
import './ChefAI.css';

const ChefAI = () => {
    const { token } = useContext(StoreContext);
    const [dishName, setDishName] = useState('');
    const [recipe, setRecipe] = useState(null);
    const [isLoading, setIsLoading] = useState(false);
    const [error, setError] = useState('');

    const handleGetRecipe = async (e) => {
        e.preventDefault();
        if (!dishName.trim()) {
            setError('Please enter a dish name.');
            return;
        }
        setIsLoading(true);
        setError('');
        setRecipe(null);
        try {
            const data = await fetchRecipe(dishName, token);
            setRecipe(data);
        } catch (err) {
            setError('Could not fetch the recipe. Please try again.');
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="container chef-ai-container py-5">
            <div className="text-center mb-4">
                <h2>Welcome to ChefAI</h2>
                <p className="lead">Your personal AI-powered cooking assistant. What would you like to make today?</p>
            </div>

            <div className="row justify-content-center">
                <div className="col-md-8">
                    <form onSubmit={handleGetRecipe} className="input-group mb-5">
                        <input
                            type="text"
                            className="form-control"
                            placeholder="e.g., Chicken Biryani, Paneer Butter Masala..."
                            value={dishName}
                            onChange={(e) => setDishName(e.target.value)}
                        />
                        <button className="btn btn-primary" type="submit" disabled={isLoading}>
                            {isLoading ? <span className="spinner-border spinner-border-sm"></span> : 'Get Recipe'}
                        </button>
                    </form>
                </div>
            </div>

            {error && <div className="alert alert-danger text-center">{error}</div>}

            {recipe && (
                <div className="card recipe-card">
                    <div className="card-body">
                        <h3 className="card-title text-center mb-4">{dishName}</h3>
                        <div className="row text-center mb-4 recipe-meta">
                            <div className="col-md-4"><strong><i className="bi bi-clock"></i> Cooking Time:</strong> {recipe.cookingTime}</div>
                            <div className="col-md-4"><strong><i className="bi bi-fire"></i> Calories:</strong> {recipe.calories}</div>
                            <div className="col-md-4"><strong><i className="bi bi-egg-fried"></i> Diet:</strong> {recipe.dietType}</div>
                        </div>

                        <hr />

                        <div className="row">
                            <div className="col-md-5">
                                <h4>Ingredients</h4>
                                <ul className="list-group list-group-flush">
                                    {recipe.ingredients.map((item, index) => (
                                        <li key={index} className="list-group-item">{item}</li>
                                    ))}
                                </ul>
                            </div>
                            <div className="col-md-7">
                                <h4>Instructions</h4>
                                <ol>
                                    {recipe.instructions.map((step, index) => (
                                        <li key={index} className="mb-2">{step}</li>
                                    ))}
                                </ol>
                            </div>
                        </div>
                    </div>
                </div>
            )}
        </div>
    );
};

export default ChefAI;