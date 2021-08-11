#  *Foodecipher*
### By: Oskar Blyt

Wouldn't it be nice to get a better idea on how to recreate your favourite food brands at home?<br>
*Foodecipher* utilizes the Nutritional Facts and ingredient list on food packaging to estimate the
proportions in which ingredients occur. <br>
All that a user must provide are information listed on the Nutritional Facts and ***N*** core nutrient/mass ratios for ***N + 1*** ingredients: the larger ***N*** is, the closer you will get to the actual recipe.
>**Example:** Some core nutrient/mass ratios for 1% fat milk (according to the USDA)
> - Protein: 3.4 g / 100 g 
> - Total Fat:     1 g / 100 g
> - Saturated Fat: 0.6 g / 100 g
> - Sugar:   5 g / 100 g

 *Foodecipher* will return the beginning of a recipe with ingredients in grams. As some ingredients may contribute little to none toward the Nutritional Facts,  users must experiment and fill in the remaining blanks.
 <br> Additionally, this project aims to explore the some of the uses of linear algebra in a Java application.

### User Stories
As a user, I would like to be able to:

> - input an arbitrary number of ingredients and  core nutrient/mass ratios for a new recipe
> - input serving size and amounts of applicable core nutrients from Nutritional Facts
> - remove an existing recipe
> - view a list of all recipes created so far
> - view the ingredient proportions for an existing recipe
> - change an ingredient in an existing recipe
> - add a new ingredient and core nutrient/mass ratio to an existing recipe
> - remove an ingredient from an existing recipe
> - save all existing recipes to a file
> - load all saved recipes from a file

### Phase 4: Task 2
Robust class: NutritionFacts - constructor

### Phase 4: Task 3
Refactoring:
- remove the association between FoodecipherGUI and RecipeList and add a RecipeList in the RecipesEditor class
- change multiplicity of RecipeEditor - FoodecipherGUI association
from 0..1 to 1
- add a unidirectional association from  RecipeTable to RecipesEditor
- remove nested RecipesObservable class and RecipesObserver interface

