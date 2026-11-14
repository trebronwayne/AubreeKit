# Aubree for React

Copy this `react/` folder into your project (for example `src/aubree/`), then:

```tsx
import { Aubree } from './aubree/Aubree';

<Aubree state="wait" size={160} />
```

All 23 state names are listed in the main README. `AUBREE_STATES` exports them as an array,
so you can build pickers or galleries. Animated WebP plays in every current browser. People who
turn on "reduce motion" get a still frame instead. `Example.tsx` wires four states to an
idle, loading, success and error flow.
