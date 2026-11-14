import { useEffect, useState } from 'react';

// Works with Vite, webpack 5 and Next.js asset imports.
// If TypeScript complains about the .webp imports, add:  declare module '*.webp';
import happy from './assets/aubree/aubree-happy.webp';
import happyStill from './assets/aubree/aubree-happy-still.webp';
import sad from './assets/aubree/aubree-sad.webp';
import sadStill from './assets/aubree/aubree-sad-still.webp';
import ask from './assets/aubree/aubree-ask.webp';
import askStill from './assets/aubree/aubree-ask-still.webp';
import wait from './assets/aubree/aubree-wait.webp';
import waitStill from './assets/aubree/aubree-wait-still.webp';
import celebrate from './assets/aubree/aubree-celebrate.webp';
import celebrateStill from './assets/aubree/aubree-celebrate-still.webp';
import sleepy from './assets/aubree/aubree-sleepy.webp';
import sleepyStill from './assets/aubree/aubree-sleepy-still.webp';
import offline from './assets/aubree/aubree-offline.webp';
import offlineStill from './assets/aubree/aubree-offline-still.webp';
import empty from './assets/aubree/aubree-empty.webp';
import emptyStill from './assets/aubree/aubree-empty-still.webp';
import searching from './assets/aubree/aubree-searching.webp';
import searchingStill from './assets/aubree/aubree-searching-still.webp';
import typing from './assets/aubree/aubree-typing.webp';
import typingStill from './assets/aubree/aubree-typing-still.webp';
import lowbattery from './assets/aubree/aubree-lowbattery.webp';
import lowbatteryStill from './assets/aubree/aubree-lowbattery-still.webp';
import welcome from './assets/aubree/aubree-welcome.webp';
import welcomeStill from './assets/aubree/aubree-welcome-still.webp';
import pleading from './assets/aubree/aubree-pleading.webp';
import pleadingStill from './assets/aubree/aubree-pleading-still.webp';
import touched from './assets/aubree/aubree-touched.webp';
import touchedStill from './assets/aubree/aubree-touched-still.webp';
import running from './assets/aubree/aubree-running.webp';
import runningStill from './assets/aubree/aubree-running-still.webp';
import pensive from './assets/aubree/aubree-pensive.webp';
import pensiveStill from './assets/aubree/aubree-pensive-still.webp';
import laughing from './assets/aubree/aubree-laughing.webp';
import laughingStill from './assets/aubree/aubree-laughing-still.webp';
import fearful from './assets/aubree/aubree-fearful.webp';
import fearfulStill from './assets/aubree/aubree-fearful-still.webp';
import screaming from './assets/aubree/aubree-screaming.webp';
import screamingStill from './assets/aubree/aubree-screaming-still.webp';
import love from './assets/aubree/aubree-love.webp';
import loveStill from './assets/aubree/aubree-love-still.webp';
import angry from './assets/aubree/aubree-angry.webp';
import angryStill from './assets/aubree/aubree-angry-still.webp';
import crying from './assets/aubree/aubree-crying.webp';
import cryingStill from './assets/aubree/aubree-crying-still.webp';
import cool from './assets/aubree/aubree-cool.webp';
import coolStill from './assets/aubree/aubree-cool-still.webp';

export const AUBREE_STATES = ['happy', 'sad', 'ask', 'wait', 'celebrate', 'sleepy', 'offline', 'empty', 'searching', 'typing', 'lowbattery', 'welcome', 'pleading', 'touched', 'running', 'pensive', 'laughing', 'fearful', 'screaming', 'love', 'angry', 'crying', 'cool'] as const;
export type AubreeState = (typeof AUBREE_STATES)[number];

const ANIMATED: Record<AubreeState, string> = { happy, sad, ask, wait, celebrate, sleepy, offline, empty, searching, typing, lowbattery, welcome, pleading, touched, running, pensive, laughing, fearful, screaming, love, angry, crying, cool };
const STILL: Record<AubreeState, string> = {
  happy: happyStill,
  sad: sadStill,
  ask: askStill,
  wait: waitStill,
  celebrate: celebrateStill,
  sleepy: sleepyStill,
  offline: offlineStill,
  empty: emptyStill,
  searching: searchingStill,
  typing: typingStill,
  lowbattery: lowbatteryStill,
  welcome: welcomeStill,
  pleading: pleadingStill,
  touched: touchedStill,
  running: runningStill,
  pensive: pensiveStill,
  laughing: laughingStill,
  fearful: fearfulStill,
  screaming: screamingStill,
  love: loveStill,
  angry: angryStill,
  crying: cryingStill,
  cool: coolStill,
};
const ALT: Record<AubreeState, string> = {
  happy: 'Aubree dancing happily',
  sad: 'Aubree crying under a rain cloud',
  ask: 'Aubree asking a question',
  wait: 'Aubree waiting with a thinking bubble',
  celebrate: 'Aubree celebrating with confetti',
  sleepy: 'Aubree sleeping with a moon and Zzz',
  offline: 'Aubree next to a crossed-out Wi-Fi symbol',
  empty: 'Aubree looking into an empty box',
  searching: 'Aubree searching with a magnifying glass',
  typing: 'Aubree typing on a laptop',
  lowbattery: 'Aubree tired next to a low battery warning',
  welcome: 'Aubree waving hello with hearts',
  pleading: 'Aubree with big shiny pleading eyes',
  touched: 'Aubree with teary happy eyes, touched',
  running: 'Aubree running fast with speed lines',
  pensive: 'Aubree looking pensive and letting out a sigh',
  laughing: 'Aubree laughing with joyful tears',
  fearful: 'Aubree looking scared with a cold sweat',
  screaming: 'Aubree screaming in shock',
  love: 'Aubree with heart eyes and floating hearts',
  angry: 'Aubree looking angry with a red face',
  crying: 'Aubree crying out loud with streaming tears',
  cool: 'Aubree wearing sunglasses',
};

function usePrefersReducedMotion(): boolean {
  const [reduced, setReduced] = useState(false);
  useEffect(() => {
    const mq = window.matchMedia('(prefers-reduced-motion: reduce)');
    const update = () => setReduced(mq.matches);
    update();
    mq.addEventListener('change', update);
    return () => mq.removeEventListener('change', update);
  }, []);
  return reduced;
}

type Props = {
  state: AubreeState;
  /** Width and height in CSS pixels. The source art is 400x400. */
  size?: number;
  className?: string;
  /** Override the default description. Pass '' if the art is purely decorative. */
  alt?: string;
};

/**
 * Aubree reacting to app state, for example <Aubree state="wait" size=160 />.
 * Changing `state` remounts the image so the new loop starts from frame one.
 * People who prefer reduced motion get the still frame instead.
 */
export function Aubree({ state, size = 200, className, alt }: Props) {
  const reduced = usePrefersReducedMotion();
  return (
    <img
      key={state}
      className={className}
      src={reduced ? STILL[state] : ANIMATED[state]}
      alt={alt ?? ALT[state]}
      width={size}
      height={size}
      draggable={false}
      style={{ display: 'block' }}
    />
  );
}

export default Aubree;
