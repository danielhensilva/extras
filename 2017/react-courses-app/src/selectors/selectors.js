export function selectAuthors(authors) {
  if (!authors || !authors.length)
    authors = [];
  return authors.map(x => {
    return {
      value: x.id,
      text: x.lastName.toUpperCase() + ', ' + x.firstName
    };
  });
}