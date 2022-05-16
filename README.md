# Shopify-Back-End-Fall-2022-Challenge
## Submission for Shopify's Back-End Development Intern Challenge

## This back-end application demonstrates crud operations for an inventory management system.

### There are five URI Endpoints available for the user.
### The application is deployed on replit and the base URL - https://shopify-back-end-fall-2022-challenge.rahul-moje.repl.co/

| URI Endpoint                      | HTTPMethod | Description                          | Additional Comments                             |
|-----------------------------------|------------|--------------------------------------|-------------------------------------------------|
| /inventory/items                  | GET        | Retrieves all items                  | Returns empty JSON array if no items present    |
| /inventory/items                  | POST       | Creates new item                     | 505 error when trying to create same item again |
| /inventory/items/{itemId}         | GET        | Retrieves item with itemId specified | 505 error when item not present                 |
| /inventory/items/{itemId}         | PUT        | Updates item with itemId specified   | 505 error when item not present                 |
| /inventory/items/{itemId}         | DELETE     | Deletes item                         | 505 error when item not present                 |
| /inventory/items/restore/{itemId} | POST       | Restores deleted item                | 505 error when item not in deleted state        |


## Request bodies

Request body for POST request on /inventory/items -
itemName, label, quantity and price are attributes required
````
{
    "itemName": "Keyboard",
    "label": "HP",
    "quantity": "44",
    "price": "24.691"
}
````

Request body for PUT request on /inventory/items/{itemId} -
itemName, label, quantity and price are attributes required
````
{
    "itemName": "Keyboard",
    "label": "HP",
    "quantity": "44",
    "price": "24.691"
}
````

Request body for DELETE request on /inventory/items/{itemId} -
deletionComment attribute is required
````
{
    "deletionComment": "Test delete"
}
````

No request body is required for POST request on /inventory/items/restore/{itemId}

