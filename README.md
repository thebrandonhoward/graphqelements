# graphqelements
Graphql Server


http://localhost:8080/graphiql?path=/graphql


    subscription GetAddedBooksSubscription {
        bookAdded {
            id
        }
    }




    query GetAllBooks{
        books {
            id
            name
            pageCount
            author {
                id
                firstName
                lastName
                reviews {
                    id
                    message
                    rating
                }
            }
        }
    }




    query GetBookByIdFiveStarReviews {
        bookById(id: "book-1") {
            id
            name
            pageCount
            author {
                id
                firstName
                lastName
                reviews(filter: 5) {
                    id
                    message
                    rating
                }
            }
        }
    }




    query GetBookById {
        bookById(id: "book-4") {
            id
            name
            pageCount
            author {
                id
                firstName
                lastName
                reviews {
                    id
                    message
                    rating
                }
            }
        }
    }





    query IntrospectionQuery {
      __schema {
        
        queryType { name }
        mutationType { name }
        subscriptionType { name }
        types {
          ...FullType
        }
        directives {
          name
          description
          
          locations
          args {
            ...InputValue
          }
        }
      }
    }

    fragment FullType on __Type {
      kind
      name
      description
      
      
      fields(includeDeprecated: true) {
        name
        description
        args {
          ...InputValue
        }
        type {
          ...TypeRef
        }
        isDeprecated
        deprecationReason
      }
      inputFields {
        ...InputValue
      }
      interfaces {
        ...TypeRef
      }
      enumValues(includeDeprecated: true) {
        name
        description
        isDeprecated
        deprecationReason
      }
      possibleTypes {
        ...TypeRef
      }
    }

    fragment InputValue on __InputValue {
      name
      description
      type { ...TypeRef }
      defaultValue
      
      
    }

    fragment TypeRef on __Type {
      kind
      name
      ofType {
        kind
        name
        ofType {
          kind
          name
          ofType {
            kind
            name
            ofType {
              kind
              name
              ofType {
                kind
                name
                ofType {
                  kind
                  name
                  ofType {
                    kind
                    name
                    ofType {
                      kind
                      name
                      ofType {
                        kind
                        name
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  


    query {
        books(after: "2", first: 2, last: 0, before: "") {
            content {
                id
                key
            }
            cursor
        
        }
    }
