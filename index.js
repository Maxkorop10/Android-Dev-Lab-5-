const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 2000;

app.use(cors());
app.use(bodyParser.json());


const authors = [
    { full_name: "F. Scott Fitzgerald", homecountry: "US" },
    { full_name: "George Orwell", homecountry: "UK" },
    { full_name: "Jane Austen", homecountry: "UK" },
    { full_name: "J.D. Salinger", homecountry: "US" },
    { full_name: "J. Rowling", homecountry: "UK" },
    { full_name: "Herman Melville", homecountry: "US" },
    { full_name: "Luis Rivera", homecountry: "Mexico" },
];

const books = [
    { authorID: 1, title: "The Great Gatsby", prod_year: "1925" },
    { authorID: 2, title: "1984", prod_year: "1949" },
    { authorID: 3, title: "Pride and Prejudice", prod_year: "1813" },
    { authorID: 4, title: "The Catcher in the Rye", prod_year: "1951" },
    { authorID: 5, title: "Harry Potter", prod_year: "1961" },
    { authorID: 6, title: "Moby Dick", prod_year: "1851" },
    { authorID: 7, title: "Legionnaire", prod_year: "2009" },
];

app.get('/api/authors', (req, res) => {
    res.json(authors);
});

app.get('/api/books', (req, res) => {
    res.json(books);
});


app.listen(port, () => {
    console.log(`Library API running at http://localhost:${port}/api/`);
});
