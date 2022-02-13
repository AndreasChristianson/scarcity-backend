delete all weapons

```bash
curl  \
  'https://scarcity-backend.fly.dev/api/weapon' \
  -H 'accept: application/hal+json' \
  -H 'Content-Type: application/json' \
  | jq "._embedded.weapons[]._links.self.href" \
  | xargs -L1 -I it curl -X DELETE it
```